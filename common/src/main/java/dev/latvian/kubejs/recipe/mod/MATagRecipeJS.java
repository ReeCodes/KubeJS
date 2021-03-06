package dev.latvian.kubejs.recipe.mod;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.kubejs.recipe.RecipeJS;
import dev.latvian.kubejs.util.ListJS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LatvianModder
 */
public class MATagRecipeJS extends RecipeJS
{
	private final List<String> pattern = new ArrayList<>();
	private final List<String> key = new ArrayList<>();

	@Override
	public void create(ListJS args)
	{
		throw new RecipeExceptionJS("Can't create recipe with this type! Use regular shaped crafting");
	}

	@Override
	public void deserialize()
	{
		for (JsonElement e : json.get("pattern").getAsJsonArray())
		{
			pattern.add(e.getAsString());
		}

		if (pattern.isEmpty())
		{
			throw new RecipeExceptionJS("Pattern is empty!");
		}

		for (Map.Entry<String, JsonElement> entry : json.get("key").getAsJsonObject().entrySet())
		{
			inputItems.add(parseIngredientItem(entry.getValue(), entry.getKey()));
			key.add(entry.getKey());
		}

		if (key.isEmpty())
		{
			throw new RecipeExceptionJS("Key map is empty!");
		}
	}

	@Override
	public void serialize()
	{
		if (serializeInputs)
		{
			JsonArray patternJson = new JsonArray();

			for (String s : pattern)
			{
				patternJson.add(s);
			}

			json.add("pattern", patternJson);

			JsonObject keyJson = new JsonObject();

			for (int i = 0; i < key.size(); i++)
			{
				keyJson.add(key.get(i), inputItems.get(i).toJson());
			}

			json.add("key", keyJson);
		}
	}
}